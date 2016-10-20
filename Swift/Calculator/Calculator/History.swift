//
//  History.swift
//  Calculator
//
//  Created by Giovanni Lenguito on 13/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class History{
    // MARK: Properties
    var dateTime: String = ""
    var calculation: String = ""
    
    init?(dateTime: String, calculation: String){
        //Initialise properties
        self.dateTime = dateTime
        self.calculation = calculation
        
        // Initialization should fail if there is no calculation or dateTime
        if dateTime.isEmpty || calculation.isEmpty {
            return nil
        }
    }
}
