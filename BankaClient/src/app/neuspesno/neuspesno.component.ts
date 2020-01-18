import { Component, OnInit } from '@angular/core';
import Swal from "sweetalert2";
import {timer} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-neuspesno',
  templateUrl: './neuspesno.component.html',
  styleUrls: ['./neuspesno.component.css']
})
export class NeuspesnoComponent implements OnInit {

  constructor(private route: Router) { }

  ngOnInit() {
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: 'Neuspesno ste platili casopis',
      showConfirmButton: false,
      timer: 4500
    });
    timer(5500).subscribe(t => location.href = 'https://localhost:4200/');
  }

}
