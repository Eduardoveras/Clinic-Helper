from django.db import models
import uuid


# Create your models here.
class Patient(models.Model):
    patientId = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    firstName = models.CharField(max_length=100)
    lastName = models.CharField(max_length=200)
    passportNumber = models.CharField(max_length=12)
    identificationNUm = models.BigIntegerField
    nationality = models.CharField(max_length=100)
    dateOfBirth = models.DateField
    email = models.EmailField
    telephone = models.CharField(max_length=15)
