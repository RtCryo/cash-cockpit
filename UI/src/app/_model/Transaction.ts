import {Tag} from "./Tag";
import {Consumer} from "./Consumer";
import {TransactionInfo} from "./TransactionInfo";
import {TransactionType} from "./TransactionType";

export class Transaction {
    id!: string;
    transactionDate!: string;
    transactionInfo!: TransactionInfo;
    transactionType!: TransactionType;
    transactionNotice!: string;
    consumer!: Consumer;
    tags!: Tag[];
    total!: string;
}
