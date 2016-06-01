<?php
    header('Content-Type: application/json');
    session_start();
    require ('dbts.php');
    
    if($_SERVER['REQUEST_METHOD']=='POST'){
    
        try {
            $dbh = new PDO("mysql:host=$hostname;
                           dbname=craigk_ticket", $username, $password);
            //echo "Connected to database.";
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
        
        $sql = "SELECT ticketid, firstname, lastname, urgency, description, email, domain,
                `date submitted`, closed, pcid, stateid FROM `craigk_ticket` . `Tickets` WHERE active = 1";
        
        $result = $dbh->query($sql);
        
        $row = $result->fetchAll(PDO::FETCH_ASSOC);
        //$row['ticketid'] = "Hello Aman";
        
        //echo json_encode($row);
        
    }
    else
    {
        try {
            $dbh = new PDO("mysql:host=$hostname;
                           dbname=craigk_ticket", $username, $password);
            //echo "Connected to database.";
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
        
        $sql = "SELECT ticketid, firstname, lastname, urgency, description, email, domain,
                `date submitted`, closed, pcid, stateid FROM `craigk_ticket` . `Tickets` WHERE active = 1";
        
        $result = $dbh->query($sql);
        
        $row = $result->fetchAll(PDO::FETCH_ASSOC);
        //$row['ticketid'] = "Hello Aman";
        
        echo json_encode($row);
        
        
    }
