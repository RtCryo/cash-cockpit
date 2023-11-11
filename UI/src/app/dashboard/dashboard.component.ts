import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ReportDto } from '../_model/ReportDto';
import { DashboardService } from '../_service/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  report: ReportDto = new ReportDto();

  constructor(private reportService: DashboardService, private messageService: MessageService) {
    reportService.getReport().subscribe({
      next: (response) => {
        this.report = response;
      },
      error: () => {
        this.messageService.add({severity: 'error', summary: 'Error', detail: "Can't load report", life: 3000})
        this.report.consumers = "0";
        this.report.tags = "0";
        this.report.transactions = "0";
        this.report.files = "0";
      }
    })
  }

}
