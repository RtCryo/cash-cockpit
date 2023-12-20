package org.myproject.cash.cockpit.handler.model.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CsvTransaction {

    @CsvBindByPosition(position = 0)
    private String bookingDate;

    @CsvBindByPosition(position = 2)
    private String transactionType;

    @CsvBindByPosition(position = 3)
    private String beneficiary;

    @CsvBindByPosition(position = 4)
    private String purpose;

    @CsvBindByPosition(position = 11)
    private String amount;


}
