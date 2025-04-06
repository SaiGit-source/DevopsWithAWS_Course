resource "aws_instance" "linux-vm" {
  ami             = var.ami
  instance_type   = var.instance_type
  key_name        = "DevOpsMar30"
  security_groups = ["default"]
  tags = {
    Name = "Variable-Linux-VM"
  }

}

