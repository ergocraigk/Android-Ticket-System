<?php

    header('Content-Type: application/json');
    
    require 'dbts.php';
    
    $request = json_decode(file_get_contents('php://input'));
    
    if($_SERVER['REQUEST_METHOD']=='POST'){
        
        try {
            $dbh = new PDO("mysql:host=$hostname;
                           dbname=craigk_ticket", $username, $password);
            //echo "Connected to database.";
        } catch (PDOException $e) {
            //echo $e->getMessage();
        }
        
        $id = (string)$request->ticketid;
        
        $sql = "SELECT `Tickets`.ticketid, `Tickets`.firstname, `Tickets`.lastname, `Tickets`.urgency, `Tickets`.description, `Tickets`.email, `Tickets`.domain, `Tickets`.`date submitted`, `notes`.note, `Tickets`.pcid, `Tickets`.stateid, `Tickets`.catagories, `Tickets`.assignedtech, `Tickets`.active
            FROM `craigk_ticket` . `Tickets`
            LEFT JOIN `craigk_ticket` . `notes`
            ON `Tickets`.ticketid = `notes`.`ticketid`
            WHERE Tickets.ticketid=$id";
        
        $result = $dbh->query($sql);
        
        $result = $result->fetchObject();
        
        echo json_encode($result);
        
    }else{
        
    }