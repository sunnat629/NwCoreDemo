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
    @Published var timeDisplay: String = "Nw Timeline: Loading..."
    @Published var manifestDisplay: String = "EXP Name: Loading..."

    func startFetching() {
               TimelineFetcher.shared.startFetchingTimeline { timeline in
                   DispatchQueue.main.async {
                       if let timestamp = timeline?.time {
                           let humanReadable = Utils.shared.timestampToHumanReadable(timestamp: timestamp)
                           self.timeDisplay = "Nw Timeline: \(humanReadable)"
                       } else {
                           // Handle the nil case, maybe provide a default value or do something else
                           self.timeDisplay =  "Timestamp is null"
                       }
                   }
               }
       }

    func stopFetching() {
        TimelineFetcher.shared.stopFetchingTimeline()
    }
    
    
    func startFetchingManifest() {
        ExpManifestFetcher.shared.fetchedManifest(manifestId: "p78x44cv1rrm23lx") { expManifestData in
            DispatchQueue.main.async {
                // Process your `expManifestData` here
                // `expManifestData` is of type `ExpManifestData?`, so you might need to unwrap it
                if let name = expManifestData?.name {
                    self.manifestDisplay =  "EXP Name: \(name)"
                } else {
                    // Handle the nil case, maybe provide a default value or do something else
                    self.manifestDisplay =  "expManifestData is null"
                }
            }
        }
    }

    func stopFetchingManifest() {
        DispatchQueue.main.async {
            self.timeDisplay = "Stopped Fetching"
        }
        TimelineFetcher.shared.stopFetchingTimeline()
    }
}
