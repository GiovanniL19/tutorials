//
//  ChecklistViewController.swift
//  Checklists
//
//  Created by Giovanni Lenguito on 17/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class ChecklistViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    
    //Returns the number of rows to display
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 100
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ChecklistItem", for: indexPath)
        
        //Get the table row with the tag 1000
        let label = cell.viewWithTag(1000) as! UILabel
        
        if indexPath.row % 5 == 0 {
            label.text = "Walk the dog"
        } else if indexPath.row % 5 == 1 {
            label.text = "Brush my teeth"
        } else if indexPath.row % 5 == 2 {
            label.text = "Learn iOS development"
        } else if indexPath.row % 5 == 3 {
            label.text = "Soccer practice"
        } else if indexPath.row % 5 == 4 {
            label.text = "Eat ice cream"
        }
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        //sets let Cell to the row at the index path
        if let cell = tableView.cellForRow(at: indexPath) {
            //if checkmark is not checked, then check
            if cell.accessoryType == .none {
                cell.accessoryType = .checkmark
            } else {
                //if checkmark is checked, then un check
                cell.accessoryType = .none
            }
        }
        
        //Deselect the row
        tableView.deselectRow(at: indexPath, animated: true)
    }
}

