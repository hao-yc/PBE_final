require 'ruby-nfc'

module Puzzle1
  class Rfid
    def read_uid
      readers = NFC::Reader.all
      return nil if readers.empty?

      readers[0].poll(IsoDep::Tag, Mifare::Classic::Tag, Mifare::Ultralight::Tag) do |tag|
        begin
          case tag
          when Mifare::Classic::Tag, Mifare::Ultralight::Tag
            return tag.uid_hex.upcase
          when IsoDep::Tag
            return tag.uid.unpack1('H*').upcase
          end
        rescue StandardError => e
          puts "Error al llegir la targeta: #{e.message}"
          return nil
        end
      end
      nil
    end
  end
end

if __FILE__ == $0
  rf = Rfid.new
  puts "Esperando para leer la tarjeta NFC..."
  uid = rf.read_uid

  if uid
    puts "UID de la tarjeta: #{uid}"
  else
    puts "No se pudo leer la tarjeta."
  end
end
