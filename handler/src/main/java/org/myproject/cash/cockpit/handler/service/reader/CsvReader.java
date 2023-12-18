package org.myproject.cash.cockpit.handler.service.reader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.myproject.cash.cockpit.handler.exception.CsvReaderException;
import org.myproject.cash.cockpit.handler.model.csv.CsvTransaction;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CsvReader {

    public List<CsvTransaction> readCsv(final byte[] csvFile) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(csvFile);
             InputStreamReader reader = new InputStreamReader(byteArrayInputStream)) {

            CsvToBean<CsvTransaction> csvToBean = new CsvToBeanBuilder<CsvTransaction>(reader)
                    .withType(CsvTransaction.class)
                    .withSkipLines(8)
                    .withFilter(line -> !"Kontostand".equals(line[0]))
                    .withSeparator(';')
                    .build();

            return csvToBean.parse();

        } catch (Exception e) {
            throw new CsvReaderException(e.getMessage());
        }
    }

}
