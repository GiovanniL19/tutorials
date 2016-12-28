//
//  ViewController.swift
//  StoreSearch
//
//  Created by Giovanni Lenguito on 21/11/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class SearchViewController: UIViewController {
    //MARK: Properties
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.contentInset = UIEdgeInsets(top: 64, left: 0, bottom: 0,right: 0)
    }
}
//MARK: Extensions
extension SearchViewController: UISearchBarDelegate {
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        print("The search text is: '\(searchBar.text!)'")
    }
}

