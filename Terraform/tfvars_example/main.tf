provider "aws" {
  region = "ca-central-1"
}

variable "instance_type" {
  description = "instance_type of AWS"
}

resource "aws_instance" "tfvars_vm" {
  ami             = "ami-02cd5b9bfb2512340"
  instance_type   = var.instance_type
  key_name        = "DevOpsMar30"
  security_groups = ["default"]
  tags = {
    Name = "Tfvars-Linux-VM"
  }
}




