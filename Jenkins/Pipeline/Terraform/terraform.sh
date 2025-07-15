#!/bin/bash

set -e

echo "🔄 Updating system packages..."
sudo apt-get update -y
sudo apt-get install -y curl unzip gnupg software-properties-common

echo "📥 Adding HashiCorp GPG key..."
curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo gpg --dearmor -o /usr/share/keyrings/hashicorp-archive-keyring.gpg

echo "📦 Adding HashiCorp repo to apt sources..."
echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] https://apt.releases.hashicorp.com $(lsb_release -cs) main" \
  | sudo tee /etc/apt/sources.list.d/hashicorp.list > /dev/null

echo "🔄 Updating package list and installing Terraform..."
sudo apt-get update -y
sudo apt-get install terraform -y

echo "✅ Verifying Terraform version..."
terraform -version

echo "🎉 Terraform installation completed!"

