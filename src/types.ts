/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { LucideIcon, AppWindow, Settings, Phone, MessageSquare, Globe, Music, Camera, Mail, LayoutGrid, Volume2, Sun, Monitor, Scissors, Lock, Accessibility } from 'lucide-react';

export type NexusElementType = 'app' | 'action' | 'folder' | 'system' | 'link';

export interface NexusElement {
  id: string;
  name: string;
  icon?: LucideIcon;
  type: NexusElementType;
  action?: string;
  color?: string;
  children?: NexusElement[]; // For Folders
}

export const INITIAL_APPS: NexusElement[] = [
  { id: 'phone', name: 'Phone', icon: Phone, type: 'app', color: '#22c55e' },
  { id: 'whatsapp', name: 'WhatsApp', icon: MessageSquare, type: 'app', color: '#4ade80' },
  { id: 'browser', name: 'Chrome', icon: Globe, type: 'app', color: '#3b82f6' },
  { id: 'settings', name: 'Settings', icon: Settings, type: 'app', color: '#64748b' },
  { id: 'music', name: 'Music', icon: Music, type: 'app', color: '#ec4899' },
  { id: 'camera', name: 'Camera', icon: Camera, type: 'app', color: '#f43f5e' },
  { id: 'mail', name: 'Mail', icon: Mail, type: 'app', color: '#f59e0b' },
];

export const SYSTEM_ACTIONS: NexusElement[] = [
  { id: 'vol_toggle', name: 'Mute/Unmute', icon: Volume2, type: 'action', action: 'toggle_volume' },
  { id: 'brightness', name: 'Brightness', icon: Sun, type: 'action', action: 'show_brightness' },
  { id: 'screenshot', name: 'Screenshot', icon: Scissors, type: 'system', action: 'screenshot' },
  { id: 'lock', name: 'Sleep', icon: Lock, type: 'system', action: 'sleep' },
  { id: 'accessibility', name: 'Assistive', icon: Accessibility, type: 'system', action: 'accessibility_reset' },
];

export const INITIAL_SIDEBAR: NexusElement[] = [
  ...SYSTEM_ACTIONS,
  { 
    id: 'fav_folder', 
    name: 'Tools', 
    type: 'folder', 
    children: [
      { id: 'calc', name: 'Calc', icon: Monitor, type: 'app' },
      { id: 'files', name: 'Files', icon: LayoutGrid, type: 'app' },
    ] 
  }
];
