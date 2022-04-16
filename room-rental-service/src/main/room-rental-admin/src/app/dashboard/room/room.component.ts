import { Component, OnInit, ViewChild } from '@angular/core';
import { LoaderComponent } from 'src/app/loader/loader.component';
import { ApiService } from 'src/app/services/api/api.service';
import { BotService } from 'src/app/services/notification-bot/bot.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  constructor(private api: ApiService, private bot: BotService) { }

  @ViewChild(LoaderComponent, { static: true })
  private loader: LoaderComponent;

  ngOnInit() {
  }

  fromDate:any;
  toDate:any;
  rooms:any[] = [];
  persistedFromDate: any;
  persistedToDate:any;

  validateAndSearch(){
    if(this.fromDate == undefined)
      this.bot.fail("Select From Date !");
    else  if(this.toDate == undefined)
      this.bot.fail("Select To Date !");
    else  this.list();
  }

  list(){
    this.loader.load();
    this.api.listRooms(this.fromDate, this.toDate).subscribe(response => {
      this.persistedFromDate = this.fromDate;
      this.persistedToDate = this.toDate;
      this.loader.resume();
      this.api.validateResponse(response, data => {
        this.rooms = data;
      }, error => {
        this.bot.fail(error);
      });
    });
  }

  book(id){
    let booking:any = {};
    let room:any = {};
    room.roomId = id;
    booking.bookingFrom = this.persistedFromDate;
    booking.bookingTo = this.persistedToDate;
    booking.room = room;
    this.loader.load();
    this.api.book(booking).subscribe(response=>{
      this.loader.resume();
      this.api.validateResponse(response, data=>{
        this.rooms = this.rooms.filter(room => room.roomId != id);
      }, error=> this.bot.fail(error));
    });
  }

}
