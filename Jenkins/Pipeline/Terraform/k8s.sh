#!/bin/bash

set -e

echo "🔄 Updating system packages..."
sudo apt-get update -y

echo "📥 Fetching latest stable version..."
KUBECTL_VERSION=$(curl -L -s https://dl.k8s.io/release/stable.txt)

echo "📥 Downloading kubectl version $KUBECTL_VERSION..."
curl -LO "https://dl.k8s.io/release/${KUBECTL_VERSION}/bin/linux/amd64/kubectl"

echo "🔐 Downloading checksum..."
curl -LO "https://dl.k8s.io/release/${KUBECTL_VERSION}/bin/linux/amd64/kubectl.sha256"

echo "✅ Verifying checksum..."
echo "$(cat kubectl.sha256)  kubectl" | sha256sum --check -

echo "🚀 Installing kubectl..."
chmod +x kubectl
sudo mv kubectl /usr/local/bin/

echo "🧪 Verifying installation..."
kubectl version --client

echo "🎉 kubectl installed successfully!"

