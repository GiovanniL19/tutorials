//
//  HistoryTableViewCell.swift
//  Calculator
//
//  Created by Giovanni Lenguito on 13/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class HistoryTableViewCell: UITableViewCell {

    // MARK: Properties
    @IBOutlet weak var DateTime: UILabel!
    @IBOutlet weak var Calculation: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
