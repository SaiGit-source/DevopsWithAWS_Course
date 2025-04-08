provider "aws" {
  region = "ca-central-1" # or your preferred AWS region like us-east-1, etc.

  # Optional if you have AWS credentials configured via CLI or environment variables
  # access_key = "YOUR_ACCESS_KEY"
  # secret_key = "YOUR_SECRET_KEY"
}

variable "ami" {
  description = "Amazon vm image value"
  default        = "ami-02cd5b9bfb2512340"
}

variable "instance_type" {
  description = "Represents the type of instance"
  default     = "t2.micro"
}

resource "aws_instance" "linux_vm" {
  ami             = var.ami
  instance_type   = var.instance_type
  key_name        = "DevOpsMar30"
  security_groups = ["default"]
  tags = {
    Name = "WindowsSetup-Linux-VM"
  }
}




