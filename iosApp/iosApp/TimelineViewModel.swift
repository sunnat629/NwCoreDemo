//
//  TimelineViewModel.swift
//  iosApp
//
//  Created by Admin on 01.03.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Combine
import SwiftUI

class TimelineViewModel: ObservableObject {
    @Published var displayText: String = "Loading..."

    func startFetching() {
               TimelineFetcher.shared.startFetchingTimeline { timeline in
                   DispatchQueue.main.async {
                       if let timestamp = timeline?.time {
                           // `timestamp` is not nil here, so we can safely pass it
                           let humanReadable = Utils.shared.timestampToHumanReadable(timestamp: timestamp)
                           self.displayText = humanReadable + ""
                       } else {
                           // Handle the nil case, maybe provide a default value or do something else
                           print("Timestamp is nil")
                       }
                   }
               }
       }

    func stopFetching() {
        TimelineFetcher.shared.stopFetchingTimeline()
    }
    
    
    func startFetchingManifest() {
        ExpMane
               TimelineFetcher.shared.startFetchingTimeline { timeline in
                   DispatchQueue.main.async {
                       self.displayText =
                   }
               }
       }

    func stopFetchingManifest() {
        TimelineFetcher.shared.stopFetchingTimeline()
    }
}
