module "eks" {           
  source  = "terraform-aws-modules/eks/aws"   # specaifies location of module from Terraform AWS registry
  version = "19.15.1"

  cluster_name                   = local.name
  cluster_endpoint_public_access = true   # to enable public access to all cluster endpoints 

  cluster_addons = {
    coredns = {
      most_recent = true  # discovery mechanism in kubernetes
    }
    kube-proxy = {  # for kubernetes API communication within the cluster for networking purpose, kube-proxy is required
      most_recent = true
    }
    vpc-cni = {
      most_recent = true
    }
  }

  # for networking
  vpc_id                   = module.vpc.vpc_id
  subnet_ids               = module.vpc.private_subnets
  control_plane_subnet_ids = module.vpc.intra_subnets

  # what type of machines you want
  eks_managed_node_group_defaults = {
    ami_type       = "AL2_x86_64"
    instance_types = ["t2.medium"]
    attach_cluster_primary_security_group = true
  }

  eks_managed_node_groups = {
    pipeline-cluster-wg = {
      min_size     = 2
      max_size     = 2
      desired_size = 2

      instance_types = ["t2.medium"]
      capacity_type  = "SPOT"

      tags = {
        ExtraTag = "full_pipeline"
      }
    }
  }

  tags = local.tags
}