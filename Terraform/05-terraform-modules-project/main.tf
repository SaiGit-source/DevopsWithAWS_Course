module "my_ec2" {
  source = "./modules/ec2"
  ami    = "ami-02cd5b9bfb2512340"
}

module "my_s3" {
  source = "./modules/s3"

}
