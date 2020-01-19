import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import {timer} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-greska',
  templateUrl: './greska.component.html',
  styleUrls: ['./greska.component.css']
})
export class GreskaComponent implements OnInit {

  constructor(private route: Router) { }

  ngOnInit() {
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: 'Greska prilikom placanja casopisa',
      showConfirmButton: false,
      timer: 4500
    });
    timer(5500).subscribe(t => location.href = 'https://localhost:4200/');
  }

}
