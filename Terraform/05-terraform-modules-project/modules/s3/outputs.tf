output "s3_bucket_name" {
  description = "The name of the S3 bucket"
  value       = aws_s3_bucket.terraform-bucket.id
}

output "s3_bucket_arn" {
  description = "The ARN of the S3 bucket"
  value       = aws_s3_bucket.terraform-bucket.arn
}
