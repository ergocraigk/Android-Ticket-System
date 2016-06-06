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
                `date submitted`, closed, pcid, stateid FROM `craigk_ticket` . `Tickets` WHERE active = 0";
        
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
                `date submitted`, closed, pcid, stateid FROM `craigk_ticket` . `Tickets` WHERE active = 0";
        
        $result = $dbh->query($sql);
        
        $row = $result->fetchAll(PDO::FETCH_ASSOC);
        //$row['ticketid'] = "Hello Aman";
        
        echo json_encode($row);
        
        
    }
