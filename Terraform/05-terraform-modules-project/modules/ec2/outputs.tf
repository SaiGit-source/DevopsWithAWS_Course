output "ec2_vm_public_ip" {
  value = aws_instance.linux_vm.public_ip
}

output "ec2_vm_private_ip" {
  value = aws_instance.linux_vm.private_ip
}

