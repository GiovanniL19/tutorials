//
//  History.swift
//  Calculator
//
//  Created by Giovanni Lenguito on 13/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class History :NSObject, NSCoding{
    // MARK: Properties
    var dateTime: NSDate
    var calculation: String = ""
    
    struct PropertyKey {
        static let dateTime = "dateTime"
        static let calculation = "calculation"
    }
    
    
    //MARK: Archiving Paths
    //Set up paths to save data
    static let DocumentsDirectory = FileManager().urls(for: .documentDirectory, in: .userDomainMask).first!
    static let ArchiveURL = DocumentsDirectory.appendingPathComponent("history")
    
    required init?(dateTime: NSDate, calculation: String){
        //Initialise properties
        self.dateTime = dateTime
        self.calculation = calculation
        
        // Initialization should fail if there is no calculation or dateTime
        if calculation.isEmpty {
            return nil
        }
        
        super.init()
    }
    
    //MARK: NSCoding
    func encode(with aCoder: NSCoder){
        //Encode the values
        aCoder.encode(dateTime, forKey: PropertyKey.dateTime)
        aCoder.encode(calculation, forKey: PropertyKey.calculation)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        // Because photo is an optional property of History, use conditional cast.
        let dateTime = aDecoder.decodeObject(forKey: PropertyKey.dateTime) as! NSDate
        let calculation = aDecoder.decodeObject(forKey: PropertyKey.calculation) as! String
        
        // Must call designated initializer.
        self.init(dateTime: dateTime, calculation: calculation)
    }
    
}
