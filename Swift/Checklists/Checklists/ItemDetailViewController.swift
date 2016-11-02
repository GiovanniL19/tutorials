//
//  AddViewController.swift
//  Checklists
//
//  Created by Giovanni Lenguito on 23/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

//MARK: Protocol Delegate
protocol ItemDetailViewControllerDelegate: class {
    //Method declarations
    func ItemDetailViewControllerDidCancel(_ controller: ItemDetailViewController)
    func ItemDetailViewController(_ controller: ItemDetailViewController, didFinishAdding item: ChecklistItem)
    func ItemDetailViewController(_ controller: ItemDetailViewController, didFinishEditing item: ChecklistItem)
}

//Class
class ItemDetailViewController: UITableViewController, UITextFieldDelegate {
    
    //MARK: Properties
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var doneBarButton: UIBarButtonItem!
    weak var delegate: ItemDetailViewControllerDelegate?
    var itemToEdit: ChecklistItem?
    
    //MARK: Table Controls
    override func tableView(_ tableView: UITableView, willSelectRowAt indexPath: IndexPath) -> IndexPath? {
        return nil
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        //Make the keyboard appear and select textField
        textField.becomeFirstResponder()
    }
    
    //MARK: Actions
    @IBAction func cancel() {
        delegate?.ItemDetailViewControllerDidCancel(self)
    }
    
    @IBAction func done() {
        if let item = itemToEdit {
            item.text = textField.text!
            delegate?.ItemDetailViewController(self, didFinishEditing: item)
        } else {
            let newItem = ChecklistItem(text: textField.text!, check: false)
            delegate?.ItemDetailViewController(self, didFinishAdding: newItem!)
        }
    }
    
    //MARK: Functions
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //if editing item change navigation title and set the text field with object text
        if let item = itemToEdit {
            title = "Edit Item"
            textField.text = item.text
        }
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range:NSRange, replacementString string: String) -> Bool {
        let oldText = textField.text! as NSString
        let newText = oldText.replacingCharacters(in: range, with: string) as NSString
        
        doneBarButton.isEnabled = (newText.length > 0)
        
        return true
    }
    
}
