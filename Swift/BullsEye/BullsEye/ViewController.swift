//
//  ViewController.swift
//  BullsEye
//
//  Created by Giovanni Lenguito on 03/10/2016.
//  Copyright © 2016 Giovanni Lenguito. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    // MARK: Properties
    var currentValue: Int = 0
    var targetValue: Int = 0
    var score: Int = 0
    var difference: Int = 0
    var round: Int = 0
    
    @IBOutlet weak var target: UILabel!
    @IBOutlet weak var slider: UISlider!
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var roundLabel: UILabel!
    
    func updateLabels(){
        target.text = String(targetValue)
        scoreLabel.text = String(score)
        roundLabel.text = String(round)
    }
    
    func startRound(){
        //Increment round
        round += 1

        //Set a random int to the targetValue from 0 to 100
        targetValue = 1 + Int(arc4random_uniform(100))
        
        //Reset the current value to default of 50 and sets the slider to this value
        currentValue = 0
        slider.value = Float(currentValue)
        updateLabels()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //Set currentValue with the default slider value
        currentValue = lroundf(slider.value)
        
        startNewGame() //can also be written like this...self.startRound(). It is same as this.startRound()
        updateLabels()
        
        //Changes slider style
        let thumbImageNormal = UIImage(named: "SliderThumb-Normal")!; slider.setThumbImage(thumbImageNormal, for: .normal)
        
        let thumbImageHighlighted = UIImage(named: "SliderThumb-Highlighted")!; slider.setThumbImage(thumbImageHighlighted, for: .highlighted)
        let insets = UIEdgeInsets(top: 0, left: 14, bottom: 0, right: 14); let trackLeftImage = UIImage(named: "SliderTrackLeft")!
        let trackLeftResizable =
            trackLeftImage.resizableImage(withCapInsets: insets); slider.setMinimumTrackImage(trackLeftResizable, for: .normal)
        
        let trackRightImage = UIImage(named: "SliderTrackRight")!; let trackRightResizable =
            trackRightImage.resizableImage(withCapInsets: insets); slider.setMaximumTrackImage(trackRightResizable, for: .normal)

    }
    
    func startNewGame(){
        score = 0
        round = 0
        startRound()
    }
    
    // MARK: Actions
    @IBAction func startOver(_ sender: UIButton) {
        startNewGame()
    }
    @IBAction func htiMe(_ sender: UIButton) {
        //Get difference between current value and target
        difference = abs(currentValue - targetValue)
        
        //Set points and score
        var points = 100 - difference
        
        //Dynamic message switch
        let title:String
        switch (difference){
            case 0:
                title = "Spot On!"
            points += 100
            case _ where difference < 5:
                title = "Very Close!"
            case _ where difference < 10:
                title = "Nearly!"
            default:
                title = "Way To Far!"
        }
        
        //Set score
        score += points
        
        //Creates the message
        let message = "You scored \(points) points"
        
        //sets up alert
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        
        //creates action for alert
        let action = UIAlertAction(title: "Okay", style: .default, handler: { action in
            self.startRound()
            })
        
        //links action to alert
        alert.addAction(action)
        
        //present alert with animation
        present(alert, animated: true, completion: nil)
    }
    
    @IBAction func sliderMoved(_ slider: UISlider) {
        //When slider value changes set the slider value to current value
        currentValue = lroundf(slider.value)
    }
    
    
}

