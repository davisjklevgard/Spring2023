-- Query 1


-- Need customer info
-- need device type
-- need service, cost, and ERT of service

SELECT c.FirstName, c.LastName, .Device, s.ServiceType, s.Cost, s.RepairTime
FROM customer as c INNER JOIN
servicerequest USING (CustomerId)
INNER JOIN
device as d USING (DeviceID)
INNER JOIN
 service as s USING (ServiceID);



-- Query 2
-- need technician
-- need device type
-- count of type repaired

SELECT 
    CONCAT(t.FirstName, ' ', t.LastName) AS `Name`,
    d.DeviceType,
    COUNT(DeviceId) AS `NumberRepaired`
FROM
    technician AS t
        INNER JOIN
    servicerequest USING (EmployeeID)
        INNER JOIN
    device AS d USING (DeviceID)
GROUP BY `Name` , d.DeviceType;





-- Query 3
-- need device type, service, notes, oldest timeconfnames

SELECT 
    d.DeviceType, s.ServiceType, sr.Notes, s.RepairTime
FROM
    device AS d
        INNER JOIN
    servicerequest AS sr USING (DeviceID)
        INNER JOIN
    service AS s USING (ServiceID)
ORDER BY sr.OrderDate;



-- Query 4
-- Count for each time a technician has worked on a device by a customer
-- needs customer name, technician name, count for times serviced devices
SELECT 
    CONCAT(c.FirstName, ' ', c.LastName) AS `Customer`,
    CONCAT(t.FirstName, ' ', t.LastName) AS `Technician`,
    COUNT(DeviceId) AS `NumberRepaired`
FROM
    customer AS c
        INNER JOIN
    servicerequest USING (CustomerID)
        INNER JOIN
    technician AS t USING (EmployeeID)
GROUP BY `Customer`;
    


