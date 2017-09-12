import {
  Component, ElementRef, EventEmitter, forwardRef, Input, OnDestroy, OnInit, Output,
  ViewChild
} from "@angular/core";
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from "@angular/forms";
declare let jQuery: any;

const DATE_PICKER_VALUE_ACCESSOR = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => CalendarComponent),
  multi: true
};

@Component({
  selector: 'app-calendar',
  templateUrl: "./calendar.component.html",
  styleUrls: ['./calendar.component.css'],
  providers: [DATE_PICKER_VALUE_ACCESSOR]
})
export class CalendarComponent implements OnInit, ControlValueAccessor, OnDestroy{

  @Input() value = '';
  @Output() dateChange = new EventEmitter();
  @ViewChild('input') input: ElementRef;

  constructor() { }

  ngOnInit() {
    jQuery(this.input.nativeElement).datepicker({dateFormat: 'dd/mm/yy',
      changeYear: true,
      changeMonth: true,
      showOn: "button", buttonImageOnly: true, buttonText: '', buttonImage: "assets/images/icons/calendar_ico.gif",
      onSelect: (value) => {
        this.value = value;
        this.onChange(value);
        this.onTouched();
        this.dateChange.next(value);
      }
    }).datepicker('setDate', this.value);
  }

  onKey(val) {
    this.value = val;
    this.dateChange.next(this.value);
  }

  private onTouched = () => {};
  private onChange: (value: string) => void = () => {};


  writeValue(date: string): void {
    this.value = date;
    jQuery(this.input.nativeElement).datepicker('setDate', date);
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState(isDisabled: boolean): void {
    if(isDisabled) {
      this.input.nativeElement.disable()
    }else {
      this.input.nativeElement.enable();
    }
  }

  ngOnDestroy(): void {
    jQuery(this.input.nativeElement).datepicker('destroy');
  }
}
