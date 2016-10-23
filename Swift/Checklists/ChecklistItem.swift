//
//  ChecklistItem.swift
//  Checklists
//
//  Created by Giovanni Lenguito on 22/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import Foundation

class ChecklistItem: NSObject{
    var text: String = ""
    var checked: Bool = false
    
    init?(text: String, check: Bool){
        self.text = text
        self.checked = check
    }
    
    func toggleChecked(){
        checked = !checked
    }

}
