//
//  ChecklistViewController.swift
//  Checklists
//
//  Created by Giovanni Lenguito on 17/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class ChecklistViewController: UITableViewController, ItemDetailViewControllerDelegate {

    //MARK: Properties
    var checklistItems: [ChecklistItem]
    var checklist: Checklist!
    
    //MARK: Persistent Data
    //Returns the path
    func documentDirectory() -> URL{
        let paths = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        return paths[0]
    }
    
    func dataFilePath() -> URL{
        return documentDirectory().appendingPathComponent("Checklist.plist")
    }
    
    //Save list item
    func saveChecklistItems() {
        //declare mutable data and archiver
        let data = NSMutableData()
        let archiver = NSKeyedArchiver(forWritingWith: data)
        
        //encode the item
        archiver.encode(checklistItems, forKey: "ChecklistItems")
        archiver.finishEncoding()
        
        //Write data
        data.write(to: dataFilePath(), atomically: true)
    }
    
    //MARK: Delegate Protocols
    //Prepage (prepare-for-segue) gives data to controller before it is displayed
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "AddItem" {
            let navigationController = segue.destination as! UINavigationController
            let controller = navigationController.topViewController as! ItemDetailViewController
            
            controller.delegate = self
        }else if segue.identifier == "EditItem"{
            let navigationController = segue.destination as! UINavigationController
            let controller = navigationController.topViewController as! ItemDetailViewController
            
            controller.delegate = self
            
            if let indexPath = tableView.indexPath(for: sender as! UITableViewCell){
                //set itemToEdit with the current object from
                controller.itemToEdit = checklistItems[indexPath.row]
            }
        }
    }
    
    func ItemDetailViewControllerDidCancel(_ controller: ItemDetailViewController) {
        dismiss(animated: true, completion: nil)
    }
    
    func ItemDetailViewController(_ controller: ItemDetailViewController, didFinishAdding item: ChecklistItem) {
        //get the last row index
        let newRowIndex = checklistItems.count
        //add item to array
        checklistItems.append(item)
        
        //get index path and add the item to the table view
        let indexPath = IndexPath(row: newRowIndex, section: 0)
        let indexPaths = [indexPath]
        tableView.insertRows(at: indexPaths, with: .automatic)
        
        //save item to document
        saveChecklistItems()
        
        //dismiss the add item controller
        dismiss(animated: true, completion: nil)
    }

    func ItemDetailViewController(_ controller: ItemDetailViewController, didFinishEditing item: ChecklistItem) {
        if let index = checklistItems.index(of: item) {
            let indexPath = IndexPath(row: index, section: 0)
            if let cell = tableView.cellForRow(at: indexPath) {
                configureText(for: cell, with: item)
            }
        }
        
        //save item to document
        saveChecklistItems()
        
        dismiss(animated: true, completion: nil)
    }
    
    //MARK: Functions
    override func viewDidLoad() {
        super.viewDidLoad()
        //sets name of title bar to selected checklist
        title = checklist.name
    }
    
    //Sets up data
    required init?(coder aDecoder: NSCoder) {
        checklistItems = [ChecklistItem]()
        super.init(coder: aDecoder)
        loadChecklistItems()
    }
    
    func loadChecklistItems(){
        let path = dataFilePath()
        if let data = try? Data(contentsOf: path) {
            let unarchiver = NSKeyedUnarchiver(forReadingWith: data)
            checklistItems = unarchiver.decodeObject(forKey: "ChecklistItems") as! [ChecklistItem]
            unarchiver.finishDecoding()
        }
    }
    
    //MARK: Configuration Functions
    
    func configCheckmark(for cell: UITableViewCell, with item: ChecklistItem) {
        let label = cell.viewWithTag(1001) as! UILabel
        
        if item.checked {
            label.text = "👍"
        } else {
            label.text = ""
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
        let item = checklistItems[indexPath.row]
        configureText(for: cell, with: item)
        configCheckmark(for: cell, with: item)
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        //toggle checkmark
        if let cell = tableView.cellForRow(at: indexPath) {
            let item = checklistItems[indexPath.row]
            item.toggleChecked()
            configCheckmark(for: cell, with: item)
        }
        
        //save item to document
        saveChecklistItems()
        
        //Deselect the row
        tableView.deselectRow(at: indexPath, animated: true)
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        
        //remove item from check list
        checklistItems.remove(at: indexPath.row)
        let indexPaths = [indexPath]
        
        //remove item to table
        tableView.deleteRows(at: indexPaths, with: .automatic)
        
        //save item to document
        saveChecklistItems()
    }    
}

