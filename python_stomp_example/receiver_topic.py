import time
import sys

import stomp

class MyListener(stomp.ConnectionListener):
    def on_error(self, headers, message):
        print('received an error "%s"' % message)
    def on_message(self, headers, message):
        print('received a message "%s"' % message)
hosts = [('localhost', 61616)]

conn = stomp.Connection(host_and_ports=hosts)
conn.set_listener('', MyListener())
conn.start()
conn.connect('admin', 'admin', wait=True,headers = {'client-id': 'clientname'} )
conn.subscribe(destination='A.B.C.D', id=1, ack='auto',headers = {'subscription-type': 'MULTICAST','durable-subscription-name':'someValue'})
#conn.subscribe({destination=config['/topic/test'], ack:'auto', 'activemq.subscriptionName':'SampleSubscription'})
#conn.subscribe(destination='/topic/testTopic', ack='auto', headers = {'activemq.subscriptionName': 'myhostname'})

conn.send(body=' '.join(sys.argv[1:]), destination='A.B.C.D')

time.sleep(2)
conn.disconnect()
