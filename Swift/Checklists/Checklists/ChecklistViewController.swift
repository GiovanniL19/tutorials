//
//  ChecklistViewController.swift
//  Checklists
//
//  Created by Giovanni Lenguito on 17/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class ChecklistViewController: UITableViewController, AddItemViewControllerDelegate {

    //MARK: Properties
    var checklistItems: [ChecklistItem]
    
    //MARK: Delegate Protocols
    //Prepage (prepare-for-segue) gives data to controller before it is displayed
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "AddItem" {
            let navigationController = segue.destination as! UINavigationController
            let controller = navigationController.topViewController as! AddItemViewController
            
            controller.delegate = self
        }else if segue.identifier == "EditItem"{
            let navigationController = segue.destination as! UINavigationController
            let controller = navigationController.topViewController as! AddItemViewController
            
            controller.delegate = self
            
            if let indexPath = tableView.indexPath(for: sender as! UITableViewCell){
                //set itemToEdit with the current object from
                controller.itemToEdit = checklistItems[indexPath.row]
            }
        }
    }
    
    func addItemViewControllerDidCancel(_ controller: AddItemViewController) {
        dismiss(animated: true, completion: nil)
    }
    
    func addItemViewController(_ controller: AddItemViewController, didFinishAdding item: ChecklistItem) {
        //get the last row index
        let newRowIndex = checklistItems.count
        //add item to array
        checklistItems.append(item)
        
        //get index path and add the item to the table view
        let indexPath = IndexPath(row: newRowIndex, section: 0)
        let indexPaths = [indexPath]
        tableView.insertRows(at: indexPaths, with: .automatic)
        
        //dismiss the add item controller
        dismiss(animated: true, completion: nil)
    }

    func addItemViewController(_ controller: AddItemViewController, didFinishEditing item: ChecklistItem) {
        if let index = checklistItems.index(of: item) {
            let indexPath = IndexPath(row: index, section: 0)
            if let cell = tableView.cellForRow(at: indexPath) {
                configureText(for: cell, with: item)
            }
        }
        dismiss(animated: true, completion: nil)
    }
    
    //MARK: Functions
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    //Sets up data
    required init?(coder aDecoder: NSCoder) {
        checklistItems = [ChecklistItem]()

        super.init(coder: aDecoder)
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
        
        //Deselect the row
        tableView.deselectRow(at: indexPath, animated: true)
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        checklistItems.remove(at: indexPath.row)
        let indexPaths = [indexPath]
        tableView.deleteRows(at: indexPaths, with: .automatic)
    }    
}

