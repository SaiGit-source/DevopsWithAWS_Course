variable "ami" {
  description = "Amazon vm image value"
  type        = string
}

variable "instance_type" {
  description = "Represents the type of instance"
  default     = "t2.micro"
}

