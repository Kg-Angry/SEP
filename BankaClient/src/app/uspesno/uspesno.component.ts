import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import {timer} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-uspesno',
  templateUrl: './uspesno.component.html',
  styleUrls: ['./uspesno.component.css']
})
export class UspesnoComponent implements OnInit {

  constructor(private route: Router) { }

  ngOnInit() {
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: 'Uspesno ste platili casopis',
      showConfirmButton: false,
      timer: 4500
    });
    timer(4500).subscribe(t => location.href = 'https://localhost:4200/');
  }

}
