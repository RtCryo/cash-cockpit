import {Component, OnInit, ViewChild} from '@angular/core';
import {MessageService} from 'primeng/api';
import {FilterDto} from '../_model/FilterDto';
import {Tag} from '../_model/Tag';
import {Transaction} from '../_model/Transaction';
import {TagService} from '../_service/tag.service';
import {TransactionService} from '../_service/transaction.service';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import {Table} from 'primeng/table';
import {TransactionType} from '../_model/TransactionType';
import {TransactionTypeService} from '../_service/transaction-type.service';
import {MessageDto} from '../_model/MessageDto';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit{

  @ViewChild('dt') table!: Table;

  transactions: Transaction[] = [];
  newTransaction: Transaction = {
    id: "",
    transactionInfo: { id: "", info: "" },
    transactionType: { id: "", type: "CUSTOM" },
    transactionNotice: "",
    consumer: { id: "", name: "" },
    tags: [],
    total: "0",
    transactionDate: ""
  }
  tags!:Tag[];
  selectedTags:Tag[] = [];
  dateRange!: FilterDto;
  loading = false;
  rangeDates: Date[] = [];
  exportColumns: any[] = [];
  cols: any[] = [];
  transaction!: Transaction;
  filteredTags:Tag[] = [];
  transactionTypes: TransactionType[] = [];
  blockedDocument: boolean = false;
  transactionDialog: boolean = false;

  constructor(
    private transactionService: TransactionService,
    private transactionTypeService: TransactionTypeService,
    private tagService: TagService,
    private messageService: MessageService) {
    this.transaction = this.newTransaction;
    this.tagService.getAllTags().subscribe({
      next: (response) => {
        this.tags = response;
      },
    });
    this.transactionTypeService.getAllTypes().subscribe({
      next: (response) => {
        this.transactionTypes = response
      }, error: () => {
        this.transactionTypes.push(this.transaction.transactionType)
      }
    });
  }

  ngOnInit(): void {
    let t = this.transactionService.getFileInfo();
    if (t != null) {
      this.createRange(t.start, t.end);
      this.getTransactionsWithDatepicker();
    }
    this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));
  }

  createRange(start: string, end: string){
    const [yearStart, monthStart, dayStart] = start.split('-');
    const [yearEnd, monthEnd, dayEnd] = end.split('-');
    this.rangeDates.push(new Date(Date.UTC(+yearStart, +monthStart - 1, +dayStart)))
    this.rangeDates.push(new Date(Date.UTC(+yearEnd, +monthEnd - 1, +dayEnd)))
  }

  getTransactionsWithDatepicker() {
    this.dateRange = new FilterDto();

    function getUTCDate(date: Date) {
      return new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
    }

    this.dateRange.localDateStart = getUTCDate(this.rangeDates[0]);
    this.dateRange.localDateEnd = getUTCDate(this.rangeDates[1]);
    this.selectedTags?.forEach(element => {
      this.dateRange.tags.push(element.id)
    });
    this.loading = true
    this.transactionService.getTransactionsWithFilter(this.dateRange).subscribe({
      next: (response) => {
        if (response.length === 0) {
          let message = "Transaction between " + this.getFormattedDateMessage(this.rangeDates[0]) + " and " + this.getFormattedDateMessage(this.rangeDates[1]) + " not found"
          if (this.selectedTags?.length > 0) {
            message += " and tags " + this.selectedTags.map(x => x.tagName)
          }
          this.messageService.add({severity: 'warn', summary: 'Warning',
          detail: message,
          life: 5000})
        }
        this.transactions = response
        this.loading = false
      },
      error: (response) => {
        this.loading = false
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
      }
    })
  }

  getFormattedDateMessage(date: Date) {
    let monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    return date.getDate() + " " + monthNames[date.getMonth()] + " " + date.getFullYear()
  }

  showModalDialog(t:Transaction) {
    this.transaction = t;
    this.transactionDialog = true;
  }

  exportPdf() {
    const doc = new jsPDF('l', 'mm', 'a4');

    const head = [['Date', 'Consumer', 'Type', 'Tags', 'Tags']];

    autoTable(doc, {
        head: head,
        body: this.toPdfFormat()
    });
    doc.save('transactions' + this.getFormattedDateMessage(this.rangeDates[0]) + '-' + this.getFormattedDateMessage(this.rangeDates[1]) + '.pdf');
  }

  toPdfFormat() {
    let data = [];

    function formatDateForPdf(date: string){
      const [year, month, day] = date.split('-');
      return day + "." + month + "." + year
    }

    let sum:number = 0;
    for (const element of this.transactions) {
      data.push([
        formatDateForPdf(element.transactionDate),
        element.consumer.name,
        element.transactionType.type,
        this.getStringFromTags(element.tags),
        element.total,
      ]);
      sum += +element.total;
    }
    data.push(['########','########', '########', '########', '########'])
    data.push(['','', '', '', this.roundToDigit(sum, 2)])
    data.push(['','', '', this.roundToDigit(this.income(), 2), this.roundToDigit(this.payout(), 2)])
    return data;
  }

  roundToDigit(num:number, digits:number){
    return Math.round(num * Math.pow(10, digits) + Number.EPSILON) / Math.pow(10, digits)
  };

  getStringFromTags(tags:Tag[]){
    let t = tags[0] ? tags[0].tagName : "";
    for(let i = 1; i < tags.length; i++){
      t += ", " + tags[i].tagName;
    }
    return t;
  }

  summa(){
    let t: number = 0;
    this.transactions.forEach(element => {
      t += +element.total
    });
    return this.roundToDigit(t,2);
  }

  income(){
    let t:number = 0;
    this.transactions.filter(p => +p.total > 0).forEach(element => {
      t += +element.total
    });
    return this.roundToDigit(t,2);
  }

  payout(){
    let t:number = 0;
    this.transactions.filter(p => +p.total < 0).forEach(element => {
      t += +element.total
    });
    return this.roundToDigit(t,2);
  }

  addNewTransaction(){
    this.transaction = this.newTransaction;
    this.transactionDialog = true;
  }

  filterTags(event: { query: any; }) {
    let filtered : any[] = [];
    let query = event.query;

    for(const element of this.tags) {
      let tag = element;
        if (tag.tagName.toLowerCase().startsWith(query.toLowerCase())) {
            filtered.push(tag);
        }
    }
    this.filteredTags = filtered;
  }

  save(){
    if(this.transaction.id.length > 0) {
      this.updateTransaction();
    } else {
      this.createNewTransaction();
    }
  }

  remove(transaction: Transaction){
    this.transactionService.deleteTransaction(transaction.id).subscribe({
      next: (msg: MessageDto) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'success', summary: 'Success', detail: msg.message, life: 3000})
        this.blockedDocument = false
      },
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.blockedDocument = false
      }
    })
    this.transactionDialog = false;
    this.getTransactionsWithDatepicker();
  }

  updateTransaction(){
    this.blockedDocument = true
    this.transactionService.updateTransaction(this.transaction).subscribe({
      next: () => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Transaction saved', life: 3000})
        this.blockedDocument = false
      },
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.blockedDocument = false
      }
    })
    this.transactionDialog = false;
    this.getTransactionsWithDatepicker();
  }

  createNewTransaction(){
    this.blockedDocument = true
    this.transactionService.saveCustomeTransaction(this.transaction).subscribe({
      next: () => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Transaction saved', life: 3000})
        this.blockedDocument = false
      },
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.blockedDocument = false
      }
    })
    this.transactionDialog = false;
    this.getTransactionsWithDatepicker();
  }

  isRemove(){
    return this.transaction.transactionType.type === "CUSTOM" && this.transaction.id.length > 0;
  }

}
