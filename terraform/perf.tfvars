environment    = "perf"
component_name = "pds-fhir-stub"
dns_name       = "pds-fhir-stub"
repo_name      = "prm-repo-pds-fhir-stub"

task_cpu    = 256
task_memory = 512
port        = 8080

service_desired_count = "1"

alb_deregistration_delay = 15

grant_access_through_vpn = true
