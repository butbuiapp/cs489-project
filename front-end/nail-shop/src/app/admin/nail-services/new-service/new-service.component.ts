import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { type NailService } from '../../../model/nail-service.model';
import { NailServiceService } from '../../../service/nail-service.service';

@Component({
  selector: 'app-new-service',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './new-service.component.html',
  styleUrl: './new-service.component.css'
})
export class NewServiceComponent {
  @Input() editingService: NailService | null = null;
  @Output() close = new EventEmitter<void>();
  @Output() serviceAdded = new EventEmitter<void>(); // Emits an event when a service is added

  nailServiceForm!: FormGroup;

  errorMessage: string | null = null; // To store the error message

  private formBuilder = inject(FormBuilder);
  private nailServiceService = inject(NailServiceService);

  ngOnInit(): void {
    this.nailServiceForm = this.formBuilder.group({
      name: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      duration: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      description: ['']
    });
    // If editing, populate form with existing data
    if (this.editingService) {
      this.nailServiceForm.patchValue(this.editingService);
    }
  }

  onSubmit() {
    this.errorMessage = null; // Reset any previous error message
    if (this.nailServiceForm.valid) {
      const nailService: NailService = this.nailServiceForm.value;
      
      if (this.editingService) {
        // Update existing service
        this.nailServiceService.updateService(this.editingService.id, nailService).subscribe(
          () => {
            this.serviceAdded.emit();
          },
          (error) => {
            this.errorMessage = 'Failed to update service. Please try again later.';
          }
        );
      } else {
        // Add new service
        this.nailServiceService.addService(nailService).subscribe(
          () => {
            this.serviceAdded.emit(); // Notify parent component
          },
          () => {
            this.errorMessage = 'Failed to add service. Please try again later.';
          }
        );
      }
    } else {
      this.errorMessage = 'Input is invalid.';
    }
  }

  onCancel() {
    this.close.emit();
  }
}
