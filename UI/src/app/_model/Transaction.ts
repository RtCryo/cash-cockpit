import { Tag } from "./Tag";
import { Consumer } from "./Consumer";
import { TransactionInfo } from "./TransactionInfo";
import { TransactionType } from "./TransactionType";
import { Sender } from "./Sender";

export class Transaction {
    id!: string;
    transactionDate!: string;
    transactionInfo!: TransactionInfo;
    transactionType!: TransactionType;
    consumer!: Consumer;
    sender!: Sender;
    tags!: Tag[];
    total!: string;
}
