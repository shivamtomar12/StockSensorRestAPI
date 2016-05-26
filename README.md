# StockSensorRestAPI
This API saves changing stock values and create alerts on reaching lower/upper threshold values.

Rest Resources:

Incoming Stock Value :

1. Reads all stocks present in the database
read: http://localhost:8080/sensorapi/stockservice/read

2. Read stocks by its value
readByValue:  http://localhost:8080/sensorapi/stockservice/readByValue/{stock value}

3. Update stock value
update: http://localhost:8080/sensorapi/stockservice/update/{old stock value}/{new stock value}

4. Create/save new stock value
create: http://localhost:8080/sensorapi/stockservice/create

5. Delete a stock
delete: http://localhost:8080/sensorapi/stockservice/delete/{stock value to delete}

Alerts Generated On Beating The Threshold Value: 

1. Read all of the alerts generated from the database
read: http://localhost:8080/sensorapi/alertsservice/read

2. Read alerts generated between two time periods
readAlertByTimeRange: http://localhost:8080/sensorapi/alertsservice/readAlertByTimeRange/{starting timestamp}/{end timestamp}
