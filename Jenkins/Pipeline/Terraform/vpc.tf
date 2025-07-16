module "vpc {
    source = "terraform-aws-modules/vpc/aws" 
    version = "~> 4.0"                           # Latest 4.x version of VPC to be used 
    
    name = local.name
    cidr = local.vpc_cidr

    azs                 = local.azs
    private_subnets     = local.private_subnets
    public_subnets      = local.public_subnets
    intra.intra_subnets = local.intra_subnets

    enable_nat_gateway = true    # if Private subnets require internet access to access external APIs or resources then we need to enable the NAT Gateway

    public_subnets_tags = {
        "kubernetes.io/role/elb" = 1
    }

    private_subnets_tags = {
        "kubernetes.io/role/internal-elb" = 1
    }
}