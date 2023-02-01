/* Query 1 */


SELECT FirstName, LastName, FlightID, SeatPrice, AVG(SeatPrice) OVER() AS AvgSeatPrice, SeatPrice-AVG(SeatPrice) OVER() AS Difference
FROM  manifestpassenger INNER JOIN passenger USING (PassID)
WHERE PassID = 13
ORDER BY FlightID;



-- Query 2
 
 SELECT FirstName, LastName, FlightID, SeatPrice, 
 (
	SELECT AVG(SeatPrice)
    FROM manifestpassenger
    WHERE PassID = 13
 ) AS AvgSeatPrice, SeatPrice-AVG(SeatPrice) OVER() AS Difference 
 FROM manifestpassenger INNER JOIN passenger USING (PassID)
 WHERE PassID = 13
 ORDER BY FlightID;


-- Query 3 
    
SELECT 
    passenger.PassID,
    FirstName,
    LastName,
    COUNT(manifestpassenger.PassID) AS NumFlights
FROM
    passenger
        LEFT JOIN
    manifestpassenger USING (PassID)
GROUP BY PassID
ORDER BY NumFlights DESC;
    
    
    -- Query 4
    
SELECT 
    FlightID,
    PickUpTime,
    EstDropOffTime,
    ActDropOffTime,
    CostPerMile,
    PickUpLat,
    PickUpLong,
    DropOffLat,
    DropOffLong,
    TypeID,
    StatusID,
    DroneID,
    RemotePilotID
FROM
    flightreservation
WHERE
    DroneID = 1
        AND YEAR(ActDropOffTime) = 2020 
UNION SELECT 
    FlightID,
    PickUpTime,
    EstDropOffTime,
    ActDropOffTime,
    CostPerMile,
    PickUpLat,
    PickUpLong,
    DropOffLat,
    DropOffLong,
    TypeID,
    StatusID,
    DroneID,
    RemotePilotID
FROM
    flightreservation
WHERE
    DroneID = 10
        AND (ActDropOffTime BETWEEN '2021-10-01' AND '2021-12-31')
ORDER BY DroneID AND PickUpTime;
    
    
    -- Query 5
    
SELECT 
    DroneID,
    SUM(LATLONGDISTANCE(PickUpLat,
            PickUpLong,
            DropOffLat,
            DropOffLong)) AS TotalDistance
FROM
    flightreservation
WHERE
    NOT ActDropOffTime IS NULL
GROUP BY DroneID
ORDER BY TotalDistance DESC;
      
	
    -- Query 6
    
SELECT 
    flightreservation.FlightID,
    PickUpTime,
    CostPerMile,
    SUM(LATLONGDISTANCE(PickUpLat,
            PickUpLong,
            DropOffLat,
            DropOffLong)) AS Distance,
    (CostPerMile * SUM(LATLONGDISTANCE(PickUpLat,
            PickUpLong,
            DropOffLat,
            DropOffLong))) AS TotalMilageCost
FROM
    flightreservation
        INNER JOIN
    manifestpassenger USING (FlightID)
WHERE
    FlightID IN (SELECT 
            FlightID
        FROM
            passgroup
                INNER JOIN
            manifestpassenger
        WHERE
            GroupTypeID = 1)
GROUP BY FlightID
ORDER BY DISTANCE;
    
    
    
    -- Query 7
    
SELECT 
    YEAR(ActDropOffTime) AS 'Year',
    COUNT(YEAR(ActDropOffTime)) AS NumFlights,
    AVG(TIMESTAMPDIFF(MINUTE,
        PickUpTime,
        ActDropOffTime)) AS Duration
FROM
    flightreservation
WHERE
    StatusID = 5
GROUP BY YEAR(ActDropOffTime)
ORDER BY YEAR(ActDropOffTime);
    
    
    
    -- Query 8 
    
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `flightreservationdist` AS
    SELECT 
        `flightreservation`.`FlightID` AS `FlightID`,
        `flightreservation`.`PickUpTime` AS `PickUpTime`,
        `flightreservation`.`EstDropOffTime` AS `EstDropOffTime`,
        `flightreservation`.`ActDropOffTime` AS `ActDropOffTime`,
        `flightreservation`.`CostPerMile` AS `CostPerMile`,
        `flightreservation`.`PickUpLat` AS `PickUpLat`,
        `flightreservation`.`PickUpLong` AS `PickUpLong`,
        `flightreservation`.`DropOffLat` AS `DropOffLat`,
        `flightreservation`.`DropOffLong` AS `DropOffLong`,
        SUM(LATLONGDISTANCE(`flightreservation`.`PickUpLat`,
                `flightreservation`.`PickUpLong`,
                `flightreservation`.`DropOffLat`,
                `flightreservation`.`DropOffLong`)) AS `Distance`,
        `flightreservation`.`TypeID` AS `TypeID`,
        `flightreservation`.`StatusID` AS `StatusID`,
        `flightreservation`.`DroneID` AS `DroneID`,
        `flightreservation`.`RemotePilotID` AS `RemotePilotID`
    FROM
        `flightreservation`;
        
        
        -- Query 9
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `passgroupaffiliates` AS
    SELECT 
        `p`.`PassID` AS `PassID`,
        `p`.`FirstName` AS `FirstName`,
        `p`.`LastName` AS `LastName`,
        `pg`.`GroupID` AS `GroupID`,
        `pg`.`Nickname` AS `NickName`,
        `pg`.`OwnerID` AS `OwnerID`,
        `pg`.`GroupTypeID` AS `GroupTypeID`
    FROM
        ((`passenger` `p`
        LEFT JOIN `passgroupmember` ON ((`p`.`PassID` = `passgroupmember`.`PassID`)))
        JOIN `passgroup` `pg`);
        