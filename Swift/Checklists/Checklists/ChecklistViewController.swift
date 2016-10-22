//
//  ChecklistViewController.swift
//  Checklists
//
//  Created by Giovanni Lenguito on 17/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class ChecklistViewController: UITableViewController {

    //MARK: Properties
    var checklistItems: [ChecklistItem]
    
    //MARK: Functions
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    //Sets up data
    required init?(coder aDecoder: NSCoder) {
        checklistItems = [ChecklistItem]()
        
        let row0item = ChecklistItem()
        row0item.text = "Walk the dog"
        row0item.checked = false
        checklistItems.append(row0item)
        
        let row1item = ChecklistItem()
        row1item.text = "Brush my teeth"
        row1item.checked = true
        checklistItems.append(row1item)
        
        let row2item = ChecklistItem()
        row2item.text = "Learn iOS development"
        row2item.checked = true
        checklistItems.append(row2item)
        
        let row3item = ChecklistItem()
        row3item.text = "Soccer practice"
        row3item.checked = false
        checklistItems.append(row3item)
        
        let row4item = ChecklistItem()
        row4item.text = "Eat ice cream"
        row4item.checked = true
        checklistItems.append(row4item)
        
        super.init(coder: aDecoder)
    }
    
    
    //MARK: Configuration Functions
    
    func configChecked(for cell: UITableViewCell, with item: ChecklistItem){
        //Set the cell checkmark true or false
        if item.checked {
            cell.accessoryType = .checkmark
        } else {
            cell.accessoryType = .none
        }
        
    }
    
    func configureText(for cell: UITableViewCell, with item: ChecklistItem) {
        let label = cell.viewWithTag(1000) as! UILabel
        label.text = item.text
    }

    
    //Returns the number of rows to display
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return checklistItems.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ChecklistItem", for: indexPath)
        
        //Get the table row with the tag 1000
        let label = cell.viewWithTag(1000) as! UILabel
        
        let item = checklistItems[indexPath.row]
        label.text = item.text
        configureText(for: cell, with: item)
        configChecked(for: cell, with: item)

        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let cell = tableView.cellForRow(at: indexPath) {
            let item = checklistItems[indexPath.row]
            item.toggleChecked()
            configChecked(for: cell, with: item)
        }
    
        //Deselect the row
        tableView.deselectRow(at: indexPath, animated: true)
    }
}

