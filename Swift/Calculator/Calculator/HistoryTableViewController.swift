//
//  HistoryTableViewController.swift
//  Calculator
//
//  Created by Giovanni Lenguito on 13/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class HistoryTableViewController: UITableViewController {

    // MARK: Properties
    var historyCollection = [History]()
    
    // MARK: Functions
    func loadHistory() -> [History]? {
        return NSKeyedUnarchiver.unarchiveObject(withFile: History.ArchiveURL.path) as? [History]
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        //set the list with history items from archive
        if let savedHistory = loadHistory(){
            historyCollection += savedHistory
            print(savedHistory)
        }
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return historyCollection.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        let cellIdentifier = "HistoryItemTableViewCell"
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as! HistoryTableViewCell
        
        // Fetches the appropriate meal for the data source layout.
        let history = historyCollection[indexPath.row]
        //cell.DateTime.text = String(history.dateTime)
        cell.Calculation.text = history.calculation
        
        return cell
    }
    

    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    

    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            historyCollection.remove(at: indexPath.row)
            saveHistory()
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }
    }
    

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    
    //MARK: NSCoding
    func saveHistory(){
        let isSuccessfulSave = NSKeyedArchiver.archiveRootObject(historyCollection, toFile: History.ArchiveURL.path)
        
        if !isSuccessfulSave{
            print("Error saving history item")
        }else{
            print("Saved History Item")
        }
    }

}
