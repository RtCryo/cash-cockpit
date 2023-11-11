import { Tag } from "./Tag";
import { Destination } from "./Destination";
import { TransactionInfo } from "./TransactionInfo";
import { TransactionType } from "./TransactionType";
import { Sender } from "./Sender";

export class Transaction {
    id!: string;
    transactionDate!: string;
    transactionInfo!: TransactionInfo;
    transactionType!: TransactionType;
    destination!: Destination;
    sender!: Sender;
    tags!: Tag[];
    total!: string;
}