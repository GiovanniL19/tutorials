//
//  ChecklistItem.swift
//  Checklists
//
//  Created by Giovanni Lenguito on 22/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import Foundation

class ChecklistItem: NSObject, NSCoding {
    var text: String = ""
    var checked: Bool = false
    
    init?(text: String, check: Bool){
        self.text = text
        self.checked = check
        super.init()
    }
    
    func toggleChecked(){
        checked = !checked
    }
    
    //NSCoding Functions
    //a ChecklistItem should save an object named “Text” that contains the value of the instance variable text, and an object named “Checked” that contains the value of the variable checked.
    func encode(with aCoder: NSCoder){
        aCoder.encode(text, forKey: "Text")
        aCoder.encode(text, forKey: "Checked")
    }
    
    //Protocol init
    required init?(coder aDecoder: NSCoder) {
        super.init()
    }

}
