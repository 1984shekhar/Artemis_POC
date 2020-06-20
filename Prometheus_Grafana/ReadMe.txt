
- Installed the 7.6 Broker via operator
- Expose the Prometheus metrics by setting the AMQ_ENABLE_METRICS_PLUGIN=true [1]
- For the pod to be properly targeted by Prometheus I had to set the following annotations and label:
   - oc annotate pod ex-aao-ss-0 prometheus.io/scrape=true
   - oc annotate pod ex-aao-ss-0 prometheus.io/port=80
   - oc label pod ex-aao-ss-0 address=ex-aao-wconsj-0-svc-rte-amq76.apps-crc.testing
- I borrowed the prometheus.yaml from AMQ Interconnect and made a slight modification for the address (attached)
    - oc apply -f prometheus.yaml
    - oc expose service/prometheus

The Service Discovery shows the following for the broker pod:

Discovered Labels	Target Labels

    __address__="10.128.0.69" __meta_kubernetes_namespace="amq76" __meta_kubernetes_pod_annotation_k8s_v1_cni_cncf_io_networks_status="[{ "name": "openshift-sdn", "interface": "eth0", "ips": [ "10.128.0.69" ], "dns": {}, "default-route": [ "10.128.0.1" ] }]" __meta_kubernetes_pod_annotation_openshift_io_scc="anyuid" __meta_kubernetes_pod_annotation_prometheus_io_port="80" __meta_kubernetes_pod_annotation_prometheus_io_scrape="true" __meta_kubernetes_pod_container_name="ex-aao-container" __meta_kubernetes_pod_controller_kind="StatefulSet" __meta_kubernetes_pod_controller_name="ex-aao-ss" __meta_kubernetes_pod_host_ip="192.168.64.2" __meta_kubernetes_pod_ip="10.128.0.69" __meta_kubernetes_pod_label_ActiveMQArtemis="ex-aao" __meta_kubernetes_pod_label_address="ex-aao-wconsj-0-svc-rte-amq76.apps-crc.testing" __meta_kubernetes_pod_label_application="ex-aao-app" __meta_kubernetes_pod_label_controller_revision_hash="ex-aao-ss-5475bdbf5f" __meta_kubernetes_pod_label_prometheus_io_scrape="true" __meta_kubernetes_pod_label_statefulset_kubernetes_io_pod_name="ex-aao-ss-0" __meta_kubernetes_pod_name="ex-aao-ss-0" __meta_kubernetes_pod_node_name="crc-45nsk-master-0" __meta_kubernetes_pod_ready="true" __meta_kubernetes_pod_uid="f1400cdf-aa91-4211-bbd6-ab0408143981" __metrics_path__="/metrics" __scheme__="http" job="broker-metrics"

	

    instance="ex-aao-wconsj-0-svc-rte-amq76.apps-crc.testing:80" job="broker-metrics"

Targets show the broker UP
broker-metrics (1/1 up) 

Endpoint	State	Labels	Last Scrape	Error
http://ex-aao-wconsj-0-svc-rte-amq76.apps-crc.testing:80/metrics
	UP	instance="ex-aao-wconsj-0-svc-rte-amq76.apps-crc.testing:80"	2.904s ago

- I borrowed the grafana.yaml from AMQ Streams (attached)
   - oc apply -f grafana.yaml
   - oc expose service/grafana
- The Grafana datasource can then be configured and pointed to the Prometheus pod's exposed URL
- The dashboard Justin provided [2] can then be imported and used to visualize the metrics

[1] https://access.redhat.com/documentation/en-us/red_hat_amq/7.6/html/deploying_amq_broker_on_openshift/assembly_br-broker-monitoring_broker-ocp#proc_br-enabling-prometheus-plugin-ocp_broker-ocp 
[2] https://github.com/artemiscloud/grafana/blob/master/ActiveMQArtemisSampleDashboard.json
