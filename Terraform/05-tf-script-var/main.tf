resource "aws_s3_bucket" "my_bucket" {
  bucket = "my-unique-bucket-name-12345-sai-dev-20250330" # make sure it's unique
  

  tags = {
    Name        = "My S3 Bucket"
    Environment = "Dev"
  }
}

