provider "aws" {
  region = "ca-central-1"
}

resource "aws_instance" "linux-vm" {
  ami             = "ami-02cd5b9bfb2512340"
  instance_type   = "t2.micro"
  key_name        = "DevOpsMar30"
  security_groups = ["default"]
  tags = {
    Name = "Terraform-test-VM"
  }

}

