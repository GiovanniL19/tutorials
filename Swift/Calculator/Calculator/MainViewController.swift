//
//  MainViewController.swift
//  Calculator
//
//  Created by Giovanni Lenguito on 10/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class MainViewController: UIViewController {
    // MARK: Properties
    var historyCollection = [History]()
    @IBOutlet weak var display: UILabel!
    
    var operandStack = [Double]()
    var operatorSymbol: String?
    var typingInProgress = false
    var operandSelected = false
    var calculation = ""
    
    var displayValue: Double {
        //Display Value set and get
        get {
            if display.text == nil || display.text == ""{
                display.text! = "0"
            }
            
            //Gets the display value
            return Double(display.text!)!
        }
        set {
            //Sets the value to the display text
            if newValue == floor(newValue) {
                display.text = "\(Int(newValue))"
            } else {
                display.text = "\(newValue)"
            }
            
            typingInProgress = false
        }
    }
    // MARK: Functions
    func loadHistory() -> [History]? {
        return NSKeyedUnarchiver.unarchiveObject(withFile: History.ArchiveURL.path) as? [History]
    }

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let savedHistory = loadHistory(){
            historyCollection += savedHistory
            print(savedHistory)
        }
        
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func operation( operation: (Double, Double) -> Double)  {
        //Updates value and stack
        displayValue = operation(operandStack.removeLast(), operandStack.removeLast())
        operandStack.append(displayValue)
    }
    
    // MARK: Actions
    @IBAction func appendDigit(_ sender: AnyObject) {
        //Listens for button click and sets data
        let digit = sender.currentTitle!;
        calculation.append(String(digit!))
        
        operandSelected = false
        
        //Updates the display value
        if typingInProgress{
            display.text! += digit!
        }else{
            display.text = digit
            typingInProgress = true
        }
    }
    
    @IBAction func operation(_ sender: AnyObject) {
        if(display.text != sender.currentTitle!){
            //When user clicks operator
            operandSelected = true
            typingInProgress = false
            operatorSymbol = sender.currentTitle!
            operandStack.append(displayValue)
            calculation.append(String(operatorSymbol!))
            display.text = sender.currentTitle!
        }
    }
    
    @IBAction func equals(_ sender: AnyObject) {
        operandSelected = false
        
        //Checks there is a operator
        if operatorSymbol != nil {
            typingInProgress = false
            
            operandStack.append(displayValue)
            
            //Does the operation when equals is clicked
            switch operatorSymbol! {
                case "+": operation() {
                    $0 + $1
                }
                case "-": operation() {
                    $1 - $0
                }
                case "×": operation() {
                    $0 * $1
                }
                case "÷": operation() {
                    $1 / $0
                }
                
                default: break
            }
            calculation.append(String("=" + String(display.text!)))
            
            //Save history item
            let date: NSDate = NSDate()
            let newHistoryItem: History = History(dateTime: date, calculation: calculation)!
            historyCollection.append(newHistoryItem)
            saveHistory()
            

        }
    }
    
    @IBAction func clear(_ sender: AnyObject) {
        //Set everything back to default
        operandStack.removeAll()
        typingInProgress = false
        operatorSymbol = nil
        displayValue = 0
        calculation = ""
    }
    
    @IBAction func del(_ sender: AnyObject) {
        //Deletes last value entered
        let length:Int = display.text!.characters.count as Int
        if calculation != ""{
            calculation.remove(at: calculation.index(before: calculation.endIndex))
        }
        
        typingInProgress = true
        
        //Checks what the last value was
        if operandSelected {
            //Removes the display text, last stack operation and symbol
            display.text! = ""
            operandStack.removeLast()
            operatorSymbol = nil
        }else{
            if length == 1{
                //If there is nothing left to delete just replace with an empty string
                display.text! = ""
            }else{
                //Deletes last character from the display text
                display.text! = String(display.text!.characters.dropLast())
            }
        }
        
        //Set operandSelect back to default incase nexr value is a number
        operandSelected = false
        
    }
    
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

