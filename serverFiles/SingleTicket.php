/**
*The MIT License (MIT)
*Copyright (c) 2016  Amaninder Singh, Scott Evenson, Craig Koch
*
*Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
*documentation files (the "Software"), to deal in the Software without restriction, including without limitation the 
*rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit 
*persons to whom the Software is furnished to do so, subject to the following conditions:
*
*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
*WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
*COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
*OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/


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
?>