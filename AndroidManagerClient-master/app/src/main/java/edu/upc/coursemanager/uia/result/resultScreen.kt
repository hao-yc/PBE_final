package edu.upc.coursemanager.uia.result
import kotlinx.serialization.json.jsonObject
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.json.JsonObject

@Composable
fun ResultScreen(
    table: String,
    filtros: Map<String, String>,
    vm: ResultViewModel = hiltViewModel()
) {
    /** Fórmula de URL (por si la quieres registrar en logs)
    URL = baseUrl + "/" + table + "?" + filtros.joinToString("&")
     */

    LaunchedEffect(Unit) { vm.load(table, filtros) }

    when (val st = vm.state) {
        is ResultState.Loading -> CircularProgressIndicator(Modifier.padding(32.dp))
        is ResultState.Error   -> Text("Error: ${st.msg}", color = MaterialTheme.colorScheme.error)
        is ResultState.Ready   -> LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(st.data) { elemento ->
                ResultCard(table, elemento.jsonObject)
            }
        }
    }
}

@Composable
private fun ResultCard(table: String, obj: JsonObject) {
    Card(Modifier.fillMaxWidth()) {
        /** Mapeo simple según tabla.
        Fórmula: campo = obj[key] ?: "-"
         */
        Column(Modifier.padding(16.dp)) {
            when (table) {
                "tasks" -> {
                    Text(text = obj["subject"]?.toString() ?: "-")
                    Text(text = obj["name"]?.toString() ?: "-")
                    Text(text = obj["date"]?.toString() ?: "-")
                }
                "timetables" -> {
                    Text(text = obj["day"]?.toString() ?: "-")
                    Text(text = obj["hour"]?.toString() ?: "-")
                    Text(text = obj["subject"]?.toString() ?: "-")
                }
                "marks" -> {
                    Text(text = obj["subject"]?.toString() ?: "-")
                    Text(text = obj["name"]?.toString() ?: "-")
                    Text(text = obj["mark"]?.toString() ?: "-")
                }
            }
        }
    }
}
